package com.nowscas.BadWolfProduction.service;

import org.springframework.stereotype.Service;

@Service
public class FileNameRedactor {
    public String replaceSpace(String filename) {
        return filename.replace(' ', '_');
    }
}
