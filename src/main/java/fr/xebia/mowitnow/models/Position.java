package fr.xebia.mowitnow.models;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@ToString
public class Position {

	Integer x;

	Integer y;
}
