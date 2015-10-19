package com.scout24.pipedsl.model

class S3DataNode(id : String, path : FileOrDirectoryPath, sched : Schedule)
  extends DataNode(id, "S3DataNode")
{

  path match {
    case FilePath(path) => addValueField("filePath", path)
    case DirectoryPath(path) => addValueField("directoryPath", path)
    case ManifestFilePath(path) => addValueField("manifestFilePath", path)
  }
  if (sched != null) {
    addReferenceField("schedule", sched)
  }
}


abstract class FileOrDirectoryPath

case class FilePath(path : String) extends FileOrDirectoryPath
case class DirectoryPath(path : String) extends FileOrDirectoryPath
case class ManifestFilePath(path : String) extends FileOrDirectoryPath

