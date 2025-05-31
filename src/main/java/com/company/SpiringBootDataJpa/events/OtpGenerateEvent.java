package com.company.SpiringBootDataJpa.events;

import com.company.SpiringBootDataJpa.entity.Users;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@ToString
@RequiredArgsConstructor
public final class OtpGenerateEvent {
    private final Users users;

}
