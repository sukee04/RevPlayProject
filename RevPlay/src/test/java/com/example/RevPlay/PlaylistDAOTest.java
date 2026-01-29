package com.example.RevPlay;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;

import com.example.RevPlay.DAO.PlaylistDAO;
import com.example.RevPlay.model.Playlist;

@ExtendWith(MockitoExtension.class)
class PlaylistDAOTest {

    @Mock
    PlaylistDAO playlistDAO;

    @Test
    void testCreatePlaylist() {
        Playlist playlist = new Playlist();
        playlist.playlistId = 1;
        playlist.playlistName = "MyPlaylist";

        when(playlistDAO.addPlaylistIntoDB(playlist)).thenReturn(playlist);

        Playlist result = playlistDAO.addPlaylistIntoDB(playlist);

        assertNotNull(result);
        assertEquals("MyPlaylist", result.playlistName);
    }
}
