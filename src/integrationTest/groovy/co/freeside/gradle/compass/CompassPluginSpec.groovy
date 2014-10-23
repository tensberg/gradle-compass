package co.freeside.gradle.compass

import com.steadystate.css.parser.CSSOMParser
import com.steadystate.css.parser.SACParserCSS3
import org.gololang.gradle.test.integration.framework.IntegrationSpec
import org.w3c.css.sac.InputSource
import org.w3c.dom.css.CSSRuleList
import spock.lang.Shared

abstract class CompassPluginSpec extends IntegrationSpec {

  @Shared parser = new CSSOMParser(new SACParserCSS3())

  protected static String localRepoLocation() {
    System.properties."localRepo.location"
  }

  protected CSSRuleList stylesheet(String path) {
    assert fileExists(path), "file $path does not exist"
    file(path).withReader { r ->
      parser.parseStyleSheet(new InputSource(r), null, null).cssRules
    }
  }
}
