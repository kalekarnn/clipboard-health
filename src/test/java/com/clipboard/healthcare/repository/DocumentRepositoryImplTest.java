package com.clipboard.healthcare.repository;

import com.clipboard.healthcare.exception.DocumentOperationException;
import com.clipboard.healthcare.mapper.DocumentMapper;
import com.clipboard.healthcare.model.Document;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.*;

@ExtendWith(MockitoExtension.class)
class DocumentRepositoryImplTest {

    @Mock
    JdbcTemplate jdbcTemplate;

    @InjectMocks
    DocumentRepositoryImpl documentRepositoryImpl;

    @Test
    void findActiveDocumentsByWorkerId() {
        Document document = new Document(1, "name", true);
        Mockito.when(jdbcTemplate.query(anyString(), any(DocumentMapper.class), anyInt())).thenReturn(Collections.singletonList(document));

        List<Document> documents = documentRepositoryImpl.findActiveDocumentsByWorkerId(1);
        Assertions.assertNotNull(documents);
        Assertions.assertEquals(1, documents.size());
        Assertions.assertEquals(document, documents.get(0));
    }

    @Test
    void findActiveDocumentsByWorkerId_exception() {
        Mockito.when(jdbcTemplate.query(anyString(), any(DocumentMapper.class), anyInt())).thenThrow(RuntimeException.class);
        assertThrows(DocumentOperationException.class, () -> documentRepositoryImpl.findActiveDocumentsByWorkerId(1));
    }
}