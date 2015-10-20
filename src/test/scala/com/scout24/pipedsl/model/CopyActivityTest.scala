package com.scout24.pipedsl.model

import codes.reactive.scalatime.ChronoUnit
import com.amazonaws.services.datapipeline.model.PutPipelineDefinitionRequest
import com.amazonaws.services.datapipeline.model.transform.PutPipelineDefinitionRequestMarshaller
import org.scalatest.{FlatSpec, Matchers}

import scala.collection.JavaConverters._

class CopyActivityTest extends  FlatSpec with Matchers  {


  "CopyActivity" should "produce correct Json" in {
    // Given a simple Data Pipeline Structure
    val source: S3DataNode = new S3DataNode("s3source", new DirectoryPath("s3://source-bucket/source-directory"), null)
    val destination: S3DataNode = new S3DataNode("s3dest", new FilePath("s3://dest-bucket/merged-data/file.gz"), null)
    val schedule: Schedule = new Schedule("mySchedule", 15, ChronoUnit.Minutes)
    val copyActivity = new CopyActivity("myCopyActivity", source, destination, schedule)

    // use AWS SDK to create an API request
    val putDefinitionReq = new PutPipelineDefinitionRequest().withPipelineId("pipeId")
                              .withPipelineObjects(copyActivity.withReferencedObjects.asJavaCollection)
    val request = new PutPipelineDefinitionRequestMarshaller().marshall(putDefinitionReq)

    // check that request has correct Json
    val reqContent = scala.io.Source.fromInputStream(request.getContent).mkString
    reqContent shouldEqual """{
                               "pipelineId": "pipeId",
                               "pipelineObjects": [
                                 {
                                   "id": "myCopyActivity",
                                   "fields": [
                                     { "key": "type", "stringValue": "CopyActivity" },
                                     { "key": "input", "refValue": "s3source" },
                                     { "key": "output", "refValue": "s3dest" },
                                     { "key": "schedule", "refValue": "mySchedule" }
                                   ]
                                 },
                                 {
                                   "id": "mySchedule",
                                   "fields": [
                                     { "key": "type", "stringValue": "Schedule" },
                                     { "key": "period", "stringValue": "15_Minutes" }
                                   ]
                                 },
                                 {
                                   "id": "s3dest",
                                   "fields": [
                                     { "key": "type", "stringValue": "S3DataNode" },
                                     { "key": "filePath", "stringValue": "s3://dest-bucket/merged-data/file.gz" }
                                   ]
                                 },
                                 {
                                   "id": "s3source",
                                   "fields": [
                                     { "key": "type", "stringValue": "S3DataNode" },
                                     { "key": "directoryPath", "stringValue": "s3://source-bucket/source-directory" }
                                   ]
                                 }
                               ]
                             }""".replaceAll("[ \n]", "").replace("_", " ")
  }
}
