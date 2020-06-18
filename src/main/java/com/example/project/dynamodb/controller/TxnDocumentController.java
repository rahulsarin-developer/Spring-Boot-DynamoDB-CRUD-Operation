package com.example.project.dynamodb.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.example.project.dynamodb.model.TxnDocument;
import com.example.project.dynamodb.service.TxnDocumentService;

@RestController
@RequestMapping("/api/v1/aws")
public class TxnDocumentController {

	private TxnDocumentService txnDocumentService;

    @Autowired
    public TxnDocumentController(TxnDocumentService txnDocumentService) {
        this.txnDocumentService = txnDocumentService;
    }

    @PostMapping(value = "/document")
    public ResponseEntity<TxnDocument> createDocument(@RequestBody TxnDocument document) {
        try {
            TxnDocument response = txnDocumentService.createDocument(document);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (AmazonServiceException e) {
            throw new ResponseStatusException(HttpStatus.valueOf(e.getStatusCode()), e.getMessage(), e);
        } catch (AmazonClientException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), e);
        }
    }

    @GetMapping(value = "/document/{documentId}")
    public ResponseEntity<TxnDocument> readDocument(@PathVariable String documentId) {
        try {
            TxnDocument response = txnDocumentService.readDocument(documentId);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (AmazonServiceException e) {
            throw new ResponseStatusException(HttpStatus.valueOf(e.getStatusCode()), e.getMessage(), e);
        } catch (AmazonClientException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), e);
        }
    }

    @PutMapping(value = "/document")
    public ResponseEntity<TxnDocument> updateDocument(@RequestBody TxnDocument document) {
        try {
            TxnDocument response = txnDocumentService.updateDocument(document);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (AmazonServiceException e) {
            throw new ResponseStatusException(HttpStatus.valueOf(e.getStatusCode()), e.getMessage(), e);
        } catch (AmazonClientException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), e);
        }
    }

    @DeleteMapping(value = "/document/{documentId}")
    public ResponseEntity deleteDocument(@PathVariable String documentId) {
        try {
        	Map<String, String> map = new HashMap<>();
        	map.put("Document ID", documentId);
        	map.put("Status", "Deleted Successfully");
        	txnDocumentService.deleteDocument(documentId);
            return ResponseEntity.status(HttpStatus.OK).body(map);
        } catch (AmazonServiceException e) {
            throw new ResponseStatusException(HttpStatus.valueOf(e.getStatusCode()), e.getMessage(), e);
        } catch (AmazonClientException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), e);
        }
    }
}
