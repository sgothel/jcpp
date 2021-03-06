package com.jogamp.gluegen.jcpp;

import java.io.FileNotFoundException;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import com.jogamp.junit.util.SingletonJunitCase;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class JavaFileSystemTest extends SingletonJunitCase {

    @Test
    public void testJavaFileSystem() throws Exception {
        final JavaFileSystem fs = new JavaFileSystem();
        VirtualFile f;

        /* Anyone who has this file on their Unix box is messed up. */
        f = fs.getFile("/foo/bar baz");
        try {
            f.getSource();	/* drop on floor */

            assertTrue("Got a source for a non-file", f.isFile());
        } catch (final FileNotFoundException e) {
            assertFalse("Got no source for a file", f.isFile());
        }

        /* We hope we have this. */
        f = fs.getFile("/usr/include/stdio.h");
        try {
            f.getSource();	/* drop on floor */

            System.out.println("Opened stdio.h");
            assertTrue("Got a source for a non-file", f.isFile());
        } catch (final FileNotFoundException e) {
            System.out.println("Failed to open stdio.h");
            assertFalse("Got no source for a file", f.isFile());
        }

    }

}
