package com.scout24.pipedsl

import com.amazonaws.services.datapipeline.model.Tag

/**
 * Created by robert on 18.10.15.
 */
object Tags {
  def useCase(value : String) = new Tag().withKey("use_case").withValue(value)
}
