package com.scout24.pipedsl.model

import com.amazonaws.services.datapipeline.model.Field

import scala.collection.mutable.MutableList
import scala.collection.mutable

class PipelineObject extends com.amazonaws.services.datapipeline.model.PipelineObject {
  var referencedObjects = mutable.Set[PipelineObject]()

  def setType(theType : String): Unit = {
    addValueField("type", theType)
  }

  def addValueField(key : String, value : String): Unit = {
    withFields(new Field().withKey(key).withStringValue(value))
  }

  def addReferenceField(key : String, other : PipelineObject): Unit = {
    withFields(new Field().withKey(key).withRefValue(other.getId))
    referencedObjects += other
  }
}
