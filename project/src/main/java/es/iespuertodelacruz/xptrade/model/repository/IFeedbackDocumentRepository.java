package es.iespuertodelacruz.xptrade.model.repository;

import es.iespuertodelacruz.xptrade.model.documents.FeedbackDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author Nabil Leon Alvarez @nalleon
 * @author Jose Maximiliano Boada Martin @mackstm
 */

public interface IFeedbackDocumentRepository extends MongoRepository<FeedbackDocument, String> {
}
