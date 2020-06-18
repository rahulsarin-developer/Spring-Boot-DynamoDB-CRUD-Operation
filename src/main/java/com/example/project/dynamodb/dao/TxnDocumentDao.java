package com.example.project.dynamodb.dao;

import com.example.project.dynamodb.model.TxnDocument;

public interface TxnDocumentDao {

	TxnDocument addDocument(TxnDocument document);

	TxnDocument readDocument(String documentId);

	TxnDocument updateDocument(TxnDocument document);

	void deleteDocument(String documentId);
}