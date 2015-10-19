package com.scout24.pipedsl.model

case class CopyActivity(id : String, input: DataNode, output: DataNode, sched: Schedule)
  extends PipelineObject(id, "CopyActivity")
{
  addReferenceField("input", input)
  addReferenceField("output", output)
  addReferenceField("schedule", sched)
}
