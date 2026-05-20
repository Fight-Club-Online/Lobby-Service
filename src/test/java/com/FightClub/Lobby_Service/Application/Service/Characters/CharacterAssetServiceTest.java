package com.FightClub.Lobby_Service.Application.Service.Characters;

import com.FightClub.Lobby_Service.Domain.Model.CharacterAssets;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import static org.assertj.core.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class CharacterAssetServiceTest {

    @InjectMocks
    private CharacterAssetService characterAssetService;

    private String baseUrl;

    @BeforeEach
    void setUp() {
        baseUrl = "http://localhost:8080";
        ReflectionTestUtils.setField(characterAssetService, "baseUrl", baseUrl);
    }

    @Test
    void testGenerateAssetUrls() {
        // Arrange
        // Act
        CharacterAssets assets = characterAssetService.generateAssetUrls();

        // Assert
        assertThat(assets).isNotNull();
    }

    @Test
    void testGenerateAssetUrlsWithCustomBaseUrl() {
        // Arrange
        String customBaseUrl = "https://api.example.com";
        ReflectionTestUtils.setField(characterAssetService, "baseUrl", customBaseUrl);

        // Act
        CharacterAssets assets = characterAssetService.generateAssetUrls();

        // Assert
        assertThat(assets).isNotNull();
    }

    @Test
    void testGetAssetUrl() {
        // Arrange
        String assetFileName = "IDLE.png";
        String expectedUrl = baseUrl + "/assets/character/" + assetFileName;

        // Act
        String assetUrl = characterAssetService.getAssetUrl(assetFileName);

        // Assert
        assertThat(assetUrl).isEqualTo(expectedUrl);
    }

    @Test
    void testGetAssetUrlWithDifferentFileName() {
        // Arrange
        String assetFileName = "RUN.png";
        String expectedUrl = baseUrl + "/assets/character/" + assetFileName;

        // Act
        String assetUrl = characterAssetService.getAssetUrl(assetFileName);

        // Assert
        assertThat(assetUrl).isEqualTo(expectedUrl);
    }

    @Test
    void testGetAssetUrlWithAttackFile() {
        // Arrange
        String assetFileName = "ATTACK1.png";

        // Act
        String assetUrl = characterAssetService.getAssetUrl(assetFileName);

        // Assert
        assertThat(assetUrl).contains("/assets/character/");
        assertThat(assetUrl).contains(assetFileName);
    }
}
