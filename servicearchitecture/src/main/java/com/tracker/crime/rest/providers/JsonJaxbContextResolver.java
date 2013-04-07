package com.tracker.crime.rest.providers;


import java.util.HashSet;

import javax.ws.rs.ext.ContextResolver;
import javax.ws.rs.ext.Provider;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.sun.jersey.api.json.JSONConfiguration;
import com.sun.jersey.api.json.JSONJAXBContext;
import com.sun.jersey.core.spi.scanning.PackageNamesScanner;
import com.sun.jersey.spi.scanning.AnnotationScannerListener;


@Provider
public class JsonJaxbContextResolver implements ContextResolver<JAXBContext> {
    
	private static final Log logger = LogFactory
	    		.getLog(JsonJaxbContextResolver.class);	
	
    private JAXBContext context = null;
    
    // private final Set<Class> types;
    private HashSet<Class<?>> jaxbClasses;
    
    // private final Class[] cTypes;
    
    public JsonJaxbContextResolver() throws Exception {
        // this.jaxbClasses = new HashSet(Arrays.asList(cTypes));
        AnnotationScannerListener asl = null;
    	try {
	        PackageNamesScanner pns = new PackageNamesScanner(new String[]  {"com.tracker.crime.dto"});
	        asl = new  AnnotationScannerListener(XmlRootElement.class, XmlType.class);
	        pns.scan(asl);
    	} catch (com.sun.jersey.core.spi.scanning.ScannerException scanExc) {
    		logger.error ("unable to scan packages and set Json java natural conversion"); 
    	}
        jaxbClasses = (asl != null) ? new HashSet(asl.getAnnotatedClasses()): null;
        //convert set to array of classes
        try {
	        if (jaxbClasses != null)  {
	           // List<Class> list = new ArrayList<Class>(jaxbClasses);
	           // Class[] cTypes = list.toArray();
	           Class[] cTypes = new Class[jaxbClasses.size()];
	           jaxbClasses.toArray(cTypes);  // unsorted array
	           // create context with "natural" json mapping and set rootUnwrapping option is set to false. 
	           // this will ensure to generate the root element in the json response. 
	           this.context = new JSONJAXBContext(JSONConfiguration.natural().rootUnwrapping(false).build(), cTypes);
			   logger.info( " JSONJAXBContext instantiated .. ");
	        } else {
	           logger.error( " JSONJAXBContext : could not detect any Jaxb Annotated ADS classes ");
	        }
	         
        } catch (javax.xml.bind.JAXBException jaxbExc) {
        	logger.error ("unable to create JSONJAXBContext and set Json java natural conversion");
        	this.context = null;
        	jaxbExc.printStackTrace();
        } catch (Exception exc) {
        	logger.error ("unable to create JSONJAXBContext and set Json java natural conversion");
        	this.context = null;
        	exc.printStackTrace();
        }
 
    } // JsonJaxbContextResolver constructor
    
    public JAXBContext getContext(Class<?> objectType) {
        return (jaxbClasses.contains(objectType)) ? context : null;
    }
    
} // JsonJaxbContextResolver