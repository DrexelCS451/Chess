package com.chess.Tests;

import com.chess.Networking.RequestUtil;
import junit.framework.Assert;
import junit.framework.TestCase;

/**
 * Created by tomer on 2/10/16.
 */
public class RequestUtilTest extends TestCase {

    public void testLookupUser() throws Exception {
        Assert.assertNull(RequestUtil.lookupUser());
    }
}