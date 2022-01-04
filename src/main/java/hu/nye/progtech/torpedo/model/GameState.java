package hu.nye.progtech.torpedo.model;

import java.util.Objects;

/**
 * Model class used to store actual game state.
 */
public class GameState {
    public static GameStateBuilder builder() {
        return new GameStateBuilder();
    }

    private MapVO playerMap;
    private MapVO aiMap;
    private boolean shouldExit;

    public GameState(MapVO currentPlayerMap, MapVO currentAIMap, boolean shouldExit) {
        this.playerMap = currentPlayerMap;
        this.aiMap = currentAIMap;
        this.shouldExit = shouldExit;
    }

    public MapVO getActualPlayerMap() {
        return playerMap;
    }

    public MapVO getActulEnemyMap() {
        return aiMap;
    }

    public void setCurrentPlayerMap(MapVO currentPlayerMap) {
        this.playerMap = currentPlayerMap;
    }

    public void setCurrentEnemyMap(MapVO currentAIMap) {
        this.aiMap = currentAIMap;
    }

    public boolean isShouldExit() {
        return shouldExit;
    }

    public void setShouldExit(boolean shouldExit) {
        this.shouldExit = shouldExit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        GameState gameState = (GameState) o;
        return shouldExit == gameState.shouldExit && playerMap.equals(gameState.playerMap) &&
                aiMap.equals(gameState.aiMap);
    }

    @Override
    public int hashCode() {
        return Objects.hash(playerMap, aiMap, shouldExit);
    }

    @Override
    public String toString() {
        return "State{" +
                "currentMap=" + playerMap +
                ", shouldExit=" + shouldExit +
                '}';
    }

    /**
     * Builder for {@link GameState}.
     */
    public static final class GameStateBuilder {
        private MapVO playerMap;
        private MapVO aiMap;
        private boolean shouldExit;

        private GameStateBuilder() {
        }

        public static GameStateBuilder builder() {
            return new GameStateBuilder();
        }

        /**
         * Building game state.
         *
         * @param currentPlayerMap player map.
         * @param currentAIMap AI map.
         * @return map representations.
         */
        public GameStateBuilder withCurrentMap(MapVO currentPlayerMap, MapVO currentAIMap) {
            this.playerMap = currentPlayerMap;
            this.aiMap = currentAIMap;
            return this;
        }

        public GameStateBuilder withShouldExit(boolean shouldExit) {
            this.shouldExit = shouldExit;
            return this;
        }

        public GameState build() {
            return new GameState(playerMap, aiMap, shouldExit);
        }
    }
}
