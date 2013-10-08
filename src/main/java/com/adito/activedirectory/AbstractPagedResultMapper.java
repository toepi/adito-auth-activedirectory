
				/*
 *  Adito
 *
 *  Copyright (C) 2003-2006 3SP LTD. All Rights Reserved
 *
 *  This program is free software; you can redistribute it and/or
 *  modify it under the terms of the GNU General Public License
 *  as published by the Free Software Foundation; either version 2 of
 *  the License, or (at your option) any later version.
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public
 *  License along with this program; if not, write to the Free Software
 *  Foundation, Inc., 675 Mass Ave, Cambridge, MA 02139, USA.
 */
			
package com.adito.activedirectory;

import java.util.LinkedList;

import javax.naming.directory.SearchResult;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

abstract class AbstractPagedResultMapper implements PagedResultMapper {
    private static final Log logger = LogFactory.getLog(AbstractPagedResultMapper.class);
    private final LinkedList<Exception> exceptions = new LinkedList<Exception>();
    
    public final void processSearchResultException(SearchResult searchResult, Exception e) {
        String dn = searchResult.getNameInNamespace();
        logger.error("Problem retrieving principal " + dn, e);
    }

    public final void processException(Exception e) {
        logger.error("Problem retrieving principal", e);
        exceptions.add(e);
    }

    public final boolean containsExceptions() {
        return !exceptions.isEmpty();
    }

    public final int getExceptionCount() {
        return exceptions.size();
    }
    
    public final Exception getLastException() {
        return exceptions.getLast();
    }
}
