package org.triangleTests.jbehave;

import org.jbehave.scenario.Scenario;

public class TriangleTests extends Scenario {

	public TriangleTests() {
		addSteps(new TriangleTestSteps());
	}
}
