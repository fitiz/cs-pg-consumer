package com.fitiz.cspgconsumer.repository;

import com.fitiz.cspgconsumer.provider.LeaderboardPgProvider;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class LeaderboardPgRepository {

    private final LeaderboardPgProvider leaderboardPgProvider;

    public LeaderboardPgRepository(LeaderboardPgProvider leaderboardPgProvider) {
        this.leaderboardPgProvider = leaderboardPgProvider;
    }

  public boolean updateStepCount(UUID userId, Integer steps) {
    return leaderboardPgProvider.updateStepCount(userId, steps);
  }
}
