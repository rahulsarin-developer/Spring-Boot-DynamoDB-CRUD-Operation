package com.example.project.dynamodb.dao;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBDeleteExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBSaveExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ExpectedAttributeValue;
import com.example.project.dynamodb.model.TxnDocument;

@Repository
public class TxnDocumentDaoImpl implements TxnDocumentDao {

	private DynamoDBMapper dynamoDBMapper;

	@Autowired
	public TxnDocumentDaoImpl(DynamoDBMapper dynamoDBMapper) {
		this.dynamoDBMapper = dynamoDBMapper;
	}

	@Override
	public TxnDocument addDocument(TxnDocument document) {
		dynamoDBMapper.save(document);
		return document;
	}

	@Override
	public TxnDocument readDocument(String documentId) {
		return dynamoDBMapper.load(TxnDocument.class, documentId);
	}

	@Override
	public TxnDocument updateDocument(TxnDocument document) {
		Map<String, ExpectedAttributeValue> expectedAttributeValueMap = new HashMap<>();
		expectedAttributeValueMap.put("documentId",
				new ExpectedAttributeValue(new AttributeValue().withS(document.getDocumentId())));
		DynamoDBSaveExpression saveExpression = new DynamoDBSaveExpression().withExpected(expectedAttributeValueMap);
		dynamoDBMapper.save(document, saveExpression);
		return document;
	}

	@Override
	public void deleteDocument(String documentId) {
		Map<String, ExpectedAttributeValue> expectedAttributeValueMap = new HashMap<>();
		expectedAttributeValueMap.put("documentId", new ExpectedAttributeValue(new AttributeValue().withS(documentId)));
		DynamoDBDeleteExpression deleteExpression = new DynamoDBDeleteExpression()
			.withExpected(expectedAttributeValueMap);
		TxnDocument txnDocument = dynamoDBMapper.load(TxnDocument.class, documentId);
		dynamoDBMapper.delete(txnDocument, deleteExpression);
	}
}
