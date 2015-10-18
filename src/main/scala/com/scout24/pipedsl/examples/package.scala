package com.scout24.pipedsl

import com.amazonaws.regions.{Regions, Region}
import com.amazonaws.services.datapipeline.DataPipelineClient
import com.amazonaws.services.datapipeline.model.{CreatePipelineRequest, PutPipelineDefinitionRequest}

package object examples {
  val region_eu_west = Region.getRegion(Regions.EU_WEST_1)
  val client = region_eu_west.createClient(classOf[DataPipelineClient], null, null)

//  val client2 = new DataPipelineClient()
//  client2.setRegion(region_eu_west)

  val pipe_id = client.createPipeline(new CreatePipelineRequest()
    .withName("Demo Pipeline - Copy DB to S3")
    .withUniqueId("DemoCopyS3DB")
    .withTags(Tags.useCase("demo"))
  ).getPipelineId
  val put_def_rq = new PutPipelineDefinitionRequest()
    .withPipelineId(pipe_id)
    .withPipelineObjects()
  client.putPipelineDefinition(put_def_rq)
}
