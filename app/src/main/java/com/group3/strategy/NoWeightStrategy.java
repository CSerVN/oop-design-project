package com.group3.strategy;

import com.group3.model.WorkoutLog;

public class NoWeightStrategy implements NextSetRecommendationStrategy {

	@Override
    public RecommendationResult calculateNextSet(WorkoutLog currentLog) {
        Integer currentReps = currentLog.getReps();
        Double currentDistance = currentLog.getDistance();
        Double currentTime = currentLog.getTime();

        // Incase 1: Bodyweight without weight
        if (currentReps != null) {
            int nextReps = currentReps + 2; 
            return new RecommendationResult(null, nextReps, null, null, "Cố gắng vượt qua giới hạn thêm 2 reps nhé!");
        }
        
        // Incase 2: Cardio
        if (currentDistance != null) {
            // Khuyến khích chạy thêm 0.5km. Nếu có thời gian, gợi ý giữ nguyên để ép tốc độ (Pace) tốt hơn.
            Double nextDistance = currentDistance + 0.5; 
            Double nextTime = (currentTime != null) ? currentTime : null; 
            
            return new RecommendationResult(null, null, nextDistance, nextTime, "Thử thách tăng thêm 500m để nâng cao sức bền tim mạch!");
        }

        // Trường hợp 3: Bài Tập tĩnh / Yoga (CHỈ CÓ Thời gian: Plank, Yoga, Stretching...)
        if (currentTime != null) {
            // Khuyến khích giữ tư thế lâu hơn 1 phút (hoặc 10 giây tùy đơn vị time bạn quy định)
            Double nextTime = currentTime + 1.0; 
            return new RecommendationResult(null, null, null, nextTime, "Giữ tư thế lâu hơn một chút, tập trung vào nhịp thở và cảm nhận cơ thể.");
        }

        return new RecommendationResult(null, null, null, null, "Không đủ dữ liệu để gợi ý.");
    }
}
