/*
 * Copyright 2002-2012 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.springframework.test.web.servlet.result;

import static org.springframework.test.util.AssertionErrors.assertTrue;

import org.hamcrest.Matcher;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.web.servlet.ModelAndView;

/**
 * Factory for assertions on the selected view. An instance of this class is
 * typically accessed via {@link MockMvcResultMatchers#view()}.
 * @since 3.2
 */
public class ViewResultMatchers {


	/**
	 * Protected constructor.
	 * Use {@link MockMvcResultMatchers#view()}.
	 */
	protected ViewResultMatchers() {
	}

	/**
	 * Assert the selected view name with the given Hamcrest {@link Matcher}.
	 */
	public ResultMatcher name(final Matcher<? super String> matcher) {
		return new ResultMatcher() {
			public void match(MvcResult result) throws Exception {
				ModelAndView mav = result.getModelAndView();
				assertTrue("No ModelAndView found", mav != null);
				MatcherAssert.assertThat("View name", mav.getViewName(), matcher);
			}
		};
	}

	/**
	 * Assert the selected view name.
	 */
	public ResultMatcher name(final String name) {
		return name(Matchers.equalTo(name));
	}

}
