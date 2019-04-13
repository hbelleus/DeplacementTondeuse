package fr.xebia.mowitnow.models;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@ToString
public class Perimetre {

	private int limiteSelonX;

	private int limiteSelonY;
}
