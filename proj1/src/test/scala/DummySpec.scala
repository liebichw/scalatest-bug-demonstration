package sdummy

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers


class DummySpec extends AnyFlatSpec with Matchers {

  behavior of "true"

  it should "be true"

  true shouldBe true 

}
