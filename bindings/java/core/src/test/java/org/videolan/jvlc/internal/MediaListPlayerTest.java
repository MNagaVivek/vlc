/*****************************************************************************
 * MediaListPlayerTest.java: VLC Java Bindings
 *****************************************************************************
 * Copyright (C) 1998-2008 the VideoLAN team
 *
 * Authors: Filippo Carone <filippo@carone.org>
 *
 *
 * $Id $
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111, USA.
 *****************************************************************************/

package org.videolan.jvlc.internal;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.videolan.jvlc.internal.LibVlc.LibVlcInstance;
import org.videolan.jvlc.internal.LibVlc.LibVlcMediaDescriptor;
import org.videolan.jvlc.internal.LibVlc.LibVlcMediaInstance;
import org.videolan.jvlc.internal.LibVlc.LibVlcMediaList;
import org.videolan.jvlc.internal.LibVlc.LibVlcMediaListPlayer;
import org.videolan.jvlc.internal.LibVlc.libvlc_exception_t;


public class MediaListPlayerTest
{
    
    private LibVlc libvlc = LibVlc.SYNC_INSTANCE;

    private LibVlcInstance libvlcInstance;
    
    private String mrl = this.getClass().getResource("/raffa_voice.ogg").getPath();


    @Before
    public void testSetup()
    {
        libvlc_exception_t exception = new libvlc_exception_t();
        libvlcInstance = libvlc.libvlc_new(0, new String[]{}, exception);
    }

    @After
    public void tearDown()
    {
        libvlc.libvlc_release(libvlcInstance);
    }

    @Test
    public void mediaListPlayerNewTest()
    {
        libvlc_exception_t exception = new libvlc_exception_t();
        LibVlcMediaListPlayer mediaListPlayer = libvlc.libvlc_media_list_player_new(libvlcInstance, exception);
        Assert.assertNotNull(mediaListPlayer);
        Assert.assertEquals(0, exception.raised);
    }
    
    @Test
    public void mediaListPlayerSetMediaListTest()
    {
        libvlc_exception_t exception = new libvlc_exception_t();
        LibVlcMediaListPlayer mediaListPlayer = libvlc.libvlc_media_list_player_new(libvlcInstance, exception);
        LibVlcMediaList mediaList = libvlc.libvlc_media_list_new(libvlcInstance, exception);
        libvlc.libvlc_media_list_player_set_media_list(mediaListPlayer, mediaList, exception);
        Assert.assertEquals(0, exception.raised);
    }
    
    @Test
    public void mediaListPlayerSetMediaListTest2()
    {
        libvlc_exception_t exception = new libvlc_exception_t();
        LibVlcMediaListPlayer mediaListPlayer = libvlc.libvlc_media_list_player_new(libvlcInstance, exception);
        LibVlcMediaList mediaList = libvlc.libvlc_media_list_new(libvlcInstance, exception);
        LibVlcMediaDescriptor mediaDescriptor = libvlc.libvlc_media_descriptor_new(libvlcInstance, mrl, exception);
        libvlc.libvlc_media_list_add_media_descriptor(mediaList, mediaDescriptor, exception);
        libvlc.libvlc_media_list_player_set_media_list(mediaListPlayer, mediaList, exception);
        Assert.assertEquals(0, exception.raised);
    }
    
    @Test
    public void mediaListPlayerIsPlayingTest()
    {
        libvlc_exception_t exception = new libvlc_exception_t();
        LibVlcMediaListPlayer mediaListPlayer = libvlc.libvlc_media_list_player_new(libvlcInstance, exception);
        int result = libvlc.libvlc_media_list_player_is_playing(mediaListPlayer, exception);
        Assert.assertEquals(0, result);
        Assert.assertEquals(0, exception.raised);
    }

}
