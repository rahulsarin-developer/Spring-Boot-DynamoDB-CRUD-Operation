package com.example.project.dynamodb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.project.dynamodb.dao.TxnDocumentDao;
import com.example.project.dynamodb.model.TxnDocument;

@Service
public class TxnDocumentServiceImpl implements TxnDocumentService {

	 private TxnDocumentDao txnDocumentDao;

	 @Autowired
	 public TxnDocumentServiceImpl(TxnDocumentDao txnDocumentDao) {
		 this.txnDocumentDao = txnDocumentDao;
	 }

	 @Override
	 public TxnDocument createDocument(TxnDocument document) {
	     return txnDocumentDao.addDocument(document);
	 }

	 @Override
	 public TxnDocument readDocument(String documentId) {
	     return txnDocumentDao.readDocument(documentId);
	 }

	 @Override
	 public TxnDocument updateDocument(TxnDocument document) {
	     return txnDocumentDao.updateDocument(document);
	 }

	 @Override
	 public void deleteDocument(String documentId) {
	 	txnDocumentDao.deleteDocument(documentId);
	 }
}
