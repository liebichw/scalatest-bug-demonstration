# scalatest-bug-demonstration
Demonstrates bug https://github.com/scalatest/scalatest/issues/1736 in scalatest

This shows that scalatest 3.1.1 (and already 3.1.0) are not declaring a required dependency on
"com.vladsch.flexmark" % "flexmark-all" % "0.35.10"

The master branch demonstrates the original situation (bug occurs for sbt 1.2.8 and scalatest 3.1.1).
The branch with-sbt-1.3 demonstrates that it also occurs for sbt 1.3, and the branch with-flexmark shows
that it disappears as soon as the explicit dependency is added.
