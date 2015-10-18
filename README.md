# pipe-dsl
Domain-specific language for defining AWS Data Pipelines. Enables semantic auto-completion and sanity checks.
Scala, Java, JVM.

Why using a typed DSL instead of plain JSon objects:
 - syntax-checking as you type
 - checking of mandatory fields
 - easy reuse of objects and configuration patterns
 - semantic auto-completion suggests just those objects and methods which make sense in each place
 - easy to enforce specific patterns, for example considering security or monitoring/alarming

Among other things, pipe-DSL automatically ensures that all PipelineObjects used in one Data Pipeline are
included in the pipeline definition just once, regardless how often they are referenced.