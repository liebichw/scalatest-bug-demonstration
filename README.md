# scalatest-bug-demonstration
Demonstrates bug https://github.com/scalatest/scalatest/issues/1736 in scalatest

This shows that scalatest 3.1.1 (and already 3.1.0) are not declaring a required dependency on
"com.vladsch.flexmark" % "flexmark-all" % "0.35.10"
