package com.scout24.pipedsl.model

import com.amazonaws.services.datapipeline.model.Field

import scala.collection.mutable

class PipelineObject(id : String, theType : String) extends AwsPipelineObject {
  setId(id)
  addValueField("type", theType)

  var referencedObjects = mutable.Set[PipelineObject]()

  def withReferencedObjects : Set[AwsPipelineObject] = collectReferencedObjects(Set[AwsPipelineObject]())

  def collectReferencedObjects(collected : Set[AwsPipelineObject]) : Set[AwsPipelineObject] = {
    if (collected.contains(this))
      collected
    else {
      referencedObjects.foldLeft(collected + this)((coll, obj) => obj.collectReferencedObjects(coll))
    }
  }

  def addValueField(key : String, value : String): Unit = {
    withFields(new Field().withKey(key).withStringValue(value))
  }

  def addReferenceField(key : String, other : PipelineObject): Unit = {
    withFields(new Field().withKey(key).withRefValue(other.getId))
    referencedObjects += other
  }
}
