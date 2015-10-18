package com.scout24.pipedsl.model

case class CopyActivity(input: DataNode, output: DataNode, sched: Schedule) extends PipelineObject {
  setType("CopyActivity")

  addReferenceField("input", input)
  addReferenceField("output", output)
  addReferenceField("schedule", sched)
}
