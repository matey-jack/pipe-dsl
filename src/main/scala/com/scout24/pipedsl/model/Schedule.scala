package com.scout24.pipedsl.model

import java.time.temporal.ChronoUnit


class Schedule(id : String, period : Int, periodUnit : ChronoUnit) extends PipelineObject(id, "Schedule") {
  addValueField("period", period.toString ++ " " ++ periodUnit.toString.capitalize)
}
