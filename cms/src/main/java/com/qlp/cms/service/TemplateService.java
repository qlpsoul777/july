package com.qlp.cms.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("templateService")
@Transactional(readOnly = true)
public class TemplateService {

}
