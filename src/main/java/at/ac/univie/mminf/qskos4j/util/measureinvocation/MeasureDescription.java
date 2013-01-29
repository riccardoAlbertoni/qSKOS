package at.ac.univie.mminf.qskos4j.util.measureinvocation;

public enum MeasureDescription {

	// Structural Issues
	OMITTED_TOP_CONCEPTS("otc",
		"Omitted Top Concepts",
		"Finds skos:ConceptSchemes without top concepts",
		"findOmittedTopConcepts"),
	TOP_CONCEPTS_HAVING_BROADER_CONCEPTS("tchbc",
		"Top Concepts Having Broader Concepts",
		"Finds top concepts internal to the vocabulary hierarchy tree",
		"findTopConceptsHavingBroaderConcepts"),	
		
	// Other Issues
	RELATION_CLASHES("rc",
		"Relation Clashes",
		"Covers condition S27 from the SKOS reference document (Associative vs. Hierarchical Relation Clashes)",
		"findRelationClashes"),
	MAPPING_CLASHES("mc",
		"Mapping Clashes",
		"Covers condition S46 from the SKOS reference document (Exact vs. Associative and Hierarchical Mapping Clashes)",
		"findMappingClashes"),



		
	public enum IssueType {STATISTICS, ISSUE}
	
	private String id, name, description, qSkosMethodName;
	private IssueType type = IssueType.ISSUE;
	
	MeasureDescription(String id, String name, String description, String qSkosMethodName) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.qSkosMethodName = qSkosMethodName;		
	}
	
	MeasureDescription(String id, String name, String description, String qSkosMethodName, IssueType type) {
		this(id, name, description, qSkosMethodName);
		this.type = type;
	}
	
	public String getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public String getDescription() {
		return description;
	}
	
	String getQSkosMethodName() {
		return qSkosMethodName;
	}
	
	public IssueType getType() {
		return type;
	}
	
	public static MeasureDescription findById(String id)
		throws UnsupportedMeasureIdException
	{
		for (MeasureDescription measureDesc : values()) {
			if (measureDesc.id.equals(id)) {
				return measureDesc;
			}
		}
		
		throw new UnsupportedMeasureIdException(id);
	}
	
}
