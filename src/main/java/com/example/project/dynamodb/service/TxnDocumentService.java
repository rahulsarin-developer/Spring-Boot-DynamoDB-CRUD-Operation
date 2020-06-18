package com.example.project.dynamodb.service;

import com.example.project.dynamodb.model.TxnDocument;

public interface TxnDocumentService {

	TxnDocument createDocument(TxnDocument document);

	TxnDocument readDocument(String documentId);

	void deleteDocument(String documentId);

	TxnDocument updateDocument(TxnDocument document);
}