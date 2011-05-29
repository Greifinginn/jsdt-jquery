package org.eclipselabs.jsdt.jquery.core.model;

import java.util.Collection;
import java.util.Set;

import org.eclipselabs.jsdt.jquery.api.JQueryApiPlugin;
import org.eclipselabs.jsdt.jquery.api.Version;
import org.eclipselabs.jsdt.jquery.core.api.DocumentationEntryVisitor;
import org.eclipselabs.jsdt.jquery.core.api.JQueryMember;
import org.eclipselabs.jsdt.jquery.core.api.MemberVisitor;



public class Property extends DocumentedMember implements JQueryMember {
  
  private final Collection<PropertySignature> signatures;

  Property(String name,
      String description,
      String longDescription,
      Collection<Example> examples,
      Set<String> categories,
      Collection<PropertySignature> signatures,
      String returnType) {
    super(name, description, longDescription, examples, categories, returnType);
    this.signatures = signatures;
  }
  
  @Override
  public <P> P accept(MemberVisitor<P> visitor) {
    return visitor.visitProperty(this);
  }
  
  @Override
  public <P> P accept(DocumentationEntryVisitor<P> visitor) {
    return visitor.visitProperty(this);
  }
  
  boolean isIncludedIn(Version version) {
    if (version == JQueryApiPlugin.MAX_VERSION) {
      return true;
    }
    for (Signature signature : this.signatures) {
      if (signature.isIncludedIn(version)) {
        return true;
      }
    }
    return false;
  }

}