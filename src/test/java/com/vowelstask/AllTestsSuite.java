package com.vowelstask;

import org.junit.platform.runner.JUnitPlatform;
import org.junit.platform.suite.api.IncludeEngines;
import org.junit.platform.suite.api.SelectPackages;
import org.junit.runner.RunWith;

@RunWith(JUnitPlatform.class)
@IncludeEngines("junit-jupiter")
@SelectPackages("com.vowelstask")
public class AllTestsSuite {
}
