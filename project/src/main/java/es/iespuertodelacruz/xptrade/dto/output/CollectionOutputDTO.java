package es.iespuertodelacruz.xptrade.dto.output;

import es.iespuertodelacruz.xptrade.dto.user.UserDTO;

import java.util.List;

public record CollectionOutputDTO(int id, List<GameCollectionOutputDTO> gameCollectionList, UserDTO user) {
}
