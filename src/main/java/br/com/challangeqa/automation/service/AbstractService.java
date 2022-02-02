package br.com.challangeqa.automation.service;

import br.com.challangeqa.automation.request.RequestUtil;
import br.com.challangeqa.automation.util.FakerUtils;
import br.com.challangeqa.automation.util.SchemaUtils;

public class AbstractService {

    protected RequestUtil requestUtil;
    protected SchemaUtils schemaUtils;
    protected FakerUtils fakerUtils;

    public AbstractService() {

        requestUtil = new RequestUtil();
        schemaUtils = new SchemaUtils();
        fakerUtils = new FakerUtils();
    }
}
