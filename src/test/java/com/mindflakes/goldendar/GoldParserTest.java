package com.mindflakes.goldendar;

import org.junit.Test;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created by IntelliJ IDEA.
 * User: nelson
 * Date: 5/14/11
 * Time: 6:12 PM
 * To change this template use File | Settings | File Templates.
 */
public class GoldParserTest{

    @Test
    public void testTrue(){
        assertThat(true,is(true));
    }

}
