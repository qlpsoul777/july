package com.qlp.core.util;

import org.junit.Assert;
import org.junit.Test;

import com.qlp.core.utils.StringUtil;

public class StringUtilTest {

	@Test
	public void testFirstCharUpper() {
		Assert.assertEquals("ThisIsAString", StringUtil.firstCharUpper("thisIsAString"));
	}

}
