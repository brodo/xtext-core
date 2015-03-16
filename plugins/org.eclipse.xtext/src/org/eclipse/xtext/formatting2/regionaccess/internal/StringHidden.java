/*******************************************************************************
 * Copyright (c) 2014 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package org.eclipse.xtext.formatting2.regionaccess.internal;

import java.util.List;

import org.eclipse.xtext.AbstractRule;
import org.eclipse.xtext.formatting2.regionaccess.IHiddenRegion;
import org.eclipse.xtext.formatting2.regionaccess.IHiddenRegionPart;

/**
 * @author Moritz Eysholdt - Initial contribution and API
 */
public class StringHidden extends StringRegion implements IHiddenRegionPart {
	private final AbstractHiddenRegion hiddenRegion;
	private final AbstractRule rule;

	protected StringHidden(AbstractHiddenRegion gap, AbstractRule rule, int offset, int lenght) {
		super((StringBasedRegionAccess) gap.getTextRegionAccess(), offset, lenght);
		this.hiddenRegion = gap;
		this.rule = rule;
	}

	@Override
	public AbstractRule getGrammarElement() {
		return rule;
	}

	@Override
	public IHiddenRegion getHiddenRegion() {
		return hiddenRegion;
	}

	@Override
	public IHiddenRegionPart getNextHiddenPart() {
		List<IHiddenRegionPart> parts = hiddenRegion.getParts();
		int i = parts.indexOf(this) + 1;
		if (i < parts.size())
			return parts.get(i);
		return null;
	}

	@Override
	public IHiddenRegionPart getPreviousHiddenPart() {
		List<IHiddenRegionPart> parts = hiddenRegion.getParts();
		int i = parts.indexOf(this) - 1;
		if (i > 0)
			return parts.get(i);
		return null;
	}

}