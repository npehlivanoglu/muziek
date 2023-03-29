package be.vdab.muziek.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.PositiveOrZero;


public record NieuweAlbumScore(@PositiveOrZero  @Min(0) @Max(10) int score) {
}
