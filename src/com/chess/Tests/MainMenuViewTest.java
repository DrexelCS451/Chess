package com.chess.Tests;

import com.chess.Layouts.MainMenuView;
import junit.framework.Assert;
import junit.framework.TestCase;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by tomer on 2/11/16.
 */
public class MainMenuViewTest extends TestCase {

    public void testGetMainMenu() throws Exception {
        Assert.assertNotNull(MainMenuView.getMainMenu(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

            }
        }, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

            }
        }, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

            }
        }));
    }
}