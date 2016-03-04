package com.chess.Tests;

import com.chess.Controllers.MainMenuController;
import junit.framework.Assert;
import junit.framework.TestCase;

/**
 * Created by tomer on 2/11/16.
 */
public class MainMenuControllerTest extends TestCase {

    public void testCreateView() throws Exception {
        Assert.assertNotNull(new MainMenuController());
    }
}