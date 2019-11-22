

package br.com.Books.Controller;

/**<b>Author</b>: Pedro Luiz da Silva Pereira<br>
 * <b>Date</b>: 28/02/2019<br>
 * <b>About</b>: This class has the responsibility to perform the interaction <br>
 * between the various items of the system since the extraction, loading, <br>
 * treatment and display of the information.*/

public class Books {	  
		  /* This section is about the list all attributes from Books object.*/
		  /** book or volume */
		  private String kind; 
		  /** Google id */
		  private String id; 
		  /** other Google code */
		  private String etag; 
		  /**URL to access the item in JSON format */
		  private String selfLink; 
		  /** Volume informations about: title */
		  private String title; 
		  /** Volume informations about: sub title */
		  private String subtitle; 
		  /** Volume informations about: authors */
		  private String authors; 
		  /** Volume informations about: publisher */
		  private String publisher; 
		  /** Volume informations about: Published Year */
		  private String publishedDate;
		  /** Volume informations about: short description */
		  private String description; 
		  /**  Volume informations about: industry Identifiers type 
		   * ISBN (International Standard Book Number) 10 */
		  private String ISBN10; 
		  /** Volume informations about: industry Identifiers type 
		   * ISBN(International Standard Book Number) 13 */
		  private String ISBN13; 
		  /** Volume informations about: industry Identifiers type 
		   * Other types identify */		  
		  private String OTHER;
		  /** Volume informations about: reading modes (text) */
		  private String readingModesText; 
		  /** Volume informations about: reading modes (image) */
		  private String readingModesImage;
		  /** Volume informations about: books pages number */
		  private String pageCount; 
		  /** Volume informations about: printing type (book) */
		  private String printType; 
		  /** Book relation categories (Acculturation, Religion); */
		  private String categories; 
		  /** Book average Rating by readers (1=low 5=high); */
		  private float averageRatin;
		  /** Number of readers that send your opinion (rating); */
		  private int ratingsCount; 
		  /** identify maturity rating ("NOT_MATURE"); */
		  private String maturityRating; 
		  /** Is allow Anonymous access this book (true/false); */
		  private String allowAnonLogging;
		  /** informations about content Version; */
		  private String contentVersion;
		  /** URL to access small thumbnails (book cover); */
		  private String imagesLinksSmallThumbnails; 
		  /** URL to access thumbnails (book cover); */
		  private String imagesLinksThubmnail; 
		  /** language book; */
		  private String language; 
		  /** URL access preview to google Books; */
		  private String previewLink;
		  /** URL access informations in google Books; */
		  private String infoLink; 
		  /** URL canonical access Volume; */
		  private String canonicalVolumeLink;
		  /** Sale information about country; */
		  private String saleInfoCountry; 
		  /** Sale information about sale ability; */
		  private String saleInfoSaleability;
		  /** Sale Information Book with E-book available? */
		  private String saleInfoisEbook; 
		  /** Access information about country; */
		  private String accessInfoCountry; 
		  /** Access Information about viewer */
		  private String accessInfoViewAbility;
		  /** Access Information about embed book in web page; */
		  private String accessInfoEmbeddable; 
		  /** Access Information about if book is public Domain; */
		  private String accessInfoPublicDomain; 
		  /** Access Information about if allow speech text; */
		  private String accessInfotextToSpeechPermission; 
		  /** if book has ePub available; */
		  private String epubIsAvailable;
		  /** if book has PDF available; */
		  private String pfIsAvailable; 
		  /** Access book to read at web; */
		  private String webReaderLink; 
		  /** Status Access View; */
		  private String accessViewStatus; 
		  /** if exists quote to share; */
		  private String quoteSharingAllowed; 
		  /** Text Snippet to Search; */
		  private String searchInfoTextSnippet; 
		  
		  /*Methods of Class start by getters and setters*/
		  
		  private sellerBooks sellerbooks;
		/**
		 * @return the kind
		 */
		public String getKind() {
			return kind;
		}
		/**
		 * @param kind the kind to set
		 */
		public void setKind(String kind) {
			this.kind = kind;
		}
		/**
		 * @return the id
		 */
		public String getId() {
			return id;
		}
		/**
		 * @param id the id to set
		 */
		public void setId(String id) {
			this.id = id;
		}
		/**
		 * @return the etag
		 */
		public String getEtag() {
			return etag;
		}
		/**
		 * @param etag the etag to set
		 */
		public void setEtag(String etag) {
			this.etag = etag;
		}
		/**
		 * @return the selfLink
		 */
		public String getSelfLink() {
			return selfLink;
		}
		/**
		 * @param selfLink the selfLink to set
		 */
		public void setSelfLink(String selfLink) {
			this.selfLink = selfLink;
		}
		/**
		 * @return the title
		 */
		public String getTitle() {
			return title;
		}
		/**
		 * @param title the title to set
		 */
		public void setTitle(String title) {
			this.title = title;
		}
		/**
		 * @return the subtitle
		 */
		public String getSubTitle() {
			return subtitle;
		}
		/**
		 * @param subtitle the subtitle to set
		 */
		public void setSubTitle(String subtitle) {
			this.subtitle = subtitle;
		}
		/**
		 * @return the authors
		 */
		public String getAuthors() {
			return authors;
		}
		/**
		 * @param authors the authors to set
		 */
		public void setAuthors(String authors) {
			this.authors = authors;
		}
		/**
		 * @return the publisher
		 */
		public String getPublisher() {
			return publisher;
		}
		/**
		 * @param publisher the publisher to set
		 */
		public void setPublisher(String publisher) {
			this.publisher = publisher;
		}
		/**
		 * @return the publishedDate
		 */
		public String getPublishedDate() {
			return publishedDate;
		}
		/**
		 * @param publishedDate the publishedDate to set
		 */
		public void setPublishedDate(String publishedDate) {
			if (publishedDate != null)
			{
				if (publishedDate.indexOf("-") == -1)
				{
					if (publishedDate.indexOf("*") == -1)
					{
						this.publishedDate = publishedDate;
					} 
					else
					{
						this.publishedDate = publishedDate.substring(0, publishedDate.indexOf("*"));
					}
					
				}
				else
				{
					if (publishedDate.indexOf("*") == -1)
					{
						this.publishedDate = publishedDate.substring(0, publishedDate.indexOf("-"));
					} 
					else
					{
						this.publishedDate = publishedDate.substring(0, publishedDate.indexOf("*"));
					}
				}
				
			} else
			{
				this.publishedDate = null ;
			}
			
		}
		/**
		 * @return the description
		 */
		public String getDescription() {
			return description;
		}
		/**
		 * @param description the description to set
		 */
		public void setDescription(String description) {
				this.description = description;
		}
		/**
		 * @return the iSBN10
		 */
		public String getISBN10() {
			return ISBN10;
		}
		/**
		 * @param iSBN10 the iSBN10 to set
		 */
		public void setISBN10(String iSBN10) {
			ISBN10 = iSBN10;
		}
		/**
		 * @return the iSBN13
		 */
		public String getISBN13() {
			return ISBN13;
		}
		/**
		 * @param iSBN13 the iSBN13 to set
		 */
		public void setISBN13(String iSBN13) {
			ISBN13 = iSBN13;
		}
		/**
		 * @return the readingModesText
		 */
		public String getReadingModesText() {
			return readingModesText;
		}
		/**
		 * @param readingModesText the readingModesText to set
		 */
		public void setReadingModesText(String readingModesText) {
			this.readingModesText = readingModesText;
		}
		/**
		 * @return the readingModesImage
		 */
		public String getReadingModesImage() {
			return readingModesImage;
		}
		/**
		 * @param readingModesImage the readingModesImage to set
		 */
		public void setReadingModesImage(String readingModesImage) {
			this.readingModesImage = readingModesImage;
		}
		/**
		 * @return the pageCount
		 */
		public String getPageCount() {
			return pageCount;
		}
		/**
		 * @param pageCount the pageCount to set
		 */
		public void setPageCount(String pageCount) {
			if (pageCount != null)
			{
				this.pageCount = pageCount;
			}else
			{
				this.pageCount = "0";
			}
		}
		/**
		 * @return the printType
		 */
		public String getPrintType() {
			return printType;
		}
		/**
		 * @param printType the printType to set
		 */
		public void setPrintType(String printType) {
			this.printType = printType;
		}
		/**
		 * @return the categories
		 */
		public String getCategories() {
			return categories;
		}
		/**
		 * @param categories the categories to set
		 */
		public void setCategories(String categories) {
			this.categories = categories;
		}
		/**
		 * @return the averageRatin
		 */
		public float getAverageRatin() {
			return averageRatin;
		}
		/**
		 * @param averageRatin the averageRatin to set
		 */
		public void setAverageRatin(float averageRatin) {
			this.averageRatin = averageRatin;
		}
		/**
		 * @return the ratingsCount
		 */
		public int getRatingsCount() {
			return ratingsCount;
		}
		/**
		 * @param ratingsCount the ratingsCount to set
		 */
		public void setRatingsCount(int ratingsCount) {
			this.ratingsCount = ratingsCount;
		}
		/**
		 * @return the maturityRating
		 */
		public String getMaturityRating() {
			return maturityRating;
		}
		/**
		 * @param maturityRating the maturityRating to set
		 */
		public void setMaturityRating(String maturityRating) {
			this.maturityRating = maturityRating;
		}
		/**
		 * @return the allowAnonLogging
		 */
		public String isAllowAnonLogging() {
			return allowAnonLogging;
		}
		/**
		 * @param allowAnonLogging the allowAnonLogging to set
		 */
		public void setAllowAnonLogging(String allowAnonLogging) {
			this.allowAnonLogging = allowAnonLogging;
		}
		/**
		 * @return the contentVersion
		 */
		public String getContentVersion() {
			return contentVersion;
		}
		/**
		 * @param contentVersion the contentVersion to set
		 */
		public void setContentVersion(String contentVersion) {
			this.contentVersion = contentVersion;
		}
		/**
		 * @return the imagesLinksSmallThumbnails
		 */
		public String getImagesLinksSmallThumbnails() {
			return imagesLinksSmallThumbnails;
		}
		/**
		 * @param imagesLinksSmallThumbnails the imagesLinksSmallThumbnails to set
		 */
		public void setImagesLinksSmallThumbnails(String imagesLinksSmallThumbnails) {
			this.imagesLinksSmallThumbnails = imagesLinksSmallThumbnails;
		}
		/**
		 * @return the imagesLinksThubmnail
		 */
		public String getImagesLinksThubmnail() {
			return imagesLinksThubmnail;
		}
		/**
		 * @param imagesLinksThubmnail the imagesLinksThubmnail to set
		 */
		public void setImagesLinksThubmnail(String imagesLinksThubmnail) {
			this.imagesLinksThubmnail = imagesLinksThubmnail;
		}
		/**
		 * @return the language
		 */
		public String getLanguage() {
			return language;
		}
		/**
		 * @param language the language to set
		 */
		public void setLanguage(String language) {
			this.language = language;
		}
		/**
		 * @return the previewLink
		 */
		public String getPreviewLink() {
			return previewLink;
		}
		/**
		 * @param previewLink the previewLink to set
		 */
		public void setPreviewLink(String previewLink) {
			this.previewLink = previewLink;
		}
		/**
		 * @return the infoLink
		 */
		public String getInfoLink() {
			return infoLink;
		}
		/**
		 * @param infoLink the infoLink to set
		 */
		public void setInfoLink(String infoLink) {
			this.infoLink = infoLink;
		}
		/**
		 * @return the canonicalVolumeLink
		 */
		public String getCanonicalVolumeLink() {
			return canonicalVolumeLink;
		}
		/**
		 * @param canonicalVolumeLink the canonicalVolumeLink to set
		 */
		public void setCanonicalVolumeLink(String canonicalVolumeLink) {
			this.canonicalVolumeLink = canonicalVolumeLink;
		}
		/**
		 * @return the saleInfoCountry
		 */
		public String getSaleInfoCountry() {
			return saleInfoCountry;
		}
		/**
		 * @param saleInfoCountry the saleInfoCountry to set
		 */
		public void setSaleInfoCountry(String saleInfoCountry) {
			this.saleInfoCountry = saleInfoCountry;
		}
		/**
		 * @return the saleInfoSaleability
		 */
		public String getSaleInfoSaleability() {
			return saleInfoSaleability;
		}
		/**
		 * @param saleInfoSaleability the saleInfoSaleability to set
		 */
		public void setSaleInfoSaleability(String saleInfoSaleability) {
			this.saleInfoSaleability = saleInfoSaleability;
		}
		/**
		 * @return the saleInfoisEbook
		 */
		public String isSaleInfoisEbook() {
			return saleInfoisEbook;
		}
		/**
		 * @param saleInfoisEbook the saleInfoisEbook to set
		 */
		public void setSaleInfoisEbook(String saleInfoisEbook) {
			this.saleInfoisEbook = saleInfoisEbook;
		}
		/**
		 * @return the accessInfoCountry
		 */
		public String getAccessInfoCountry() {
			return accessInfoCountry;
		}
		/**
		 * @param accessInfoCountry the accessInfoCountry to set
		 */
		public void setAccessInfoCountry(String accessInfoCountry) {
			this.accessInfoCountry = accessInfoCountry;
		}
		/**
		 * @return the accessInfoViewAbility
		 */
		public String getAccessInfoViewAbility() {
			return accessInfoViewAbility;
		}
		/**
		 * @param accessInfoViewAbility the accessInfoViewAbility to set
		 */
		public void setAccessInfoViewAbility(String accessInfoViewAbility) {
			this.accessInfoViewAbility = accessInfoViewAbility;
		}
		/**
		 * @return the accessInfoEmbeddable
		 */
		public String getAccessInfoEmbeddable() {
			return accessInfoEmbeddable;
		}
		/**
		 * @param accessInfoEmbeddable the accessInfoEmbeddable to set
		 */
		public void setAccessInfoEmbeddable(String accessInfoEmbeddable) {
			this.accessInfoEmbeddable = accessInfoEmbeddable;
		}
		/**
		 * @return the accessInfoPublicDomain
		 */
		public String isAccessInfoPublicDomain() {
			return accessInfoPublicDomain;
		}
		/**
		 * @param accessInfoPublicDomain the accessInfoPublicDomain to set
		 */
		public void setAccessInfoPublicDomain(String accessInfoPublicDomain) {
			this.accessInfoPublicDomain = accessInfoPublicDomain;
		}
		/**
		 * @return the accessInfotextToSpeechPermission
		 */
		public String getAccessInfotextToSpeechPermission() {
			return accessInfotextToSpeechPermission;
		}
		/**
		 * @param accessInfotextToSpeechPermission the accessInfotextToSpeechPermission to set
		 */
		public void setAccessInfotextToSpeechPermission(
				String accessInfotextToSpeechPermission) {
			this.accessInfotextToSpeechPermission = accessInfotextToSpeechPermission;
		}
		/**
		 * @return the epubIsAvailable
		 */
		public String isEpubIsAvailable() {
			return epubIsAvailable;
		}
		/**
		 * @param epubIsAvailable the epubIsAvailable to set
		 */
		public void setEpubIsAvailable(String epubIsAvailable) {
			this.epubIsAvailable = epubIsAvailable;
		}
		/**
		 * @return the pfIsAvailable
		 */
		public String isPfIsAvailable() {
			return pfIsAvailable;
		}
		/**
		 * @param pfIsAvailable the pfIsAvailable to set
		 */
		public void setPfIsAvailable(String pfIsAvailable) {
			this.pfIsAvailable = pfIsAvailable;
		}
		/**
		 * @return the webReaderLink
		 */
		public String getWebReaderLink() {
			return webReaderLink;
		}
		/**
		 * @param webReaderLink the webReaderLink to set
		 */
		public void setWebReaderLink(String webReaderLink) {
			this.webReaderLink = webReaderLink;
		}
		/**
		 * @return the accessViewStatus
		 */
		public String getAccessViewStatus() {
			return accessViewStatus;
		}
		/**
		 * @param accessViewStatus the accessViewStatus to set
		 */
		public void setAccessViewStatus(String accessViewStatus) {
			this.accessViewStatus = accessViewStatus;
		}
		/**
		 * @return the quoteSharingAllowed
		 */
		public String isQuoteSharingAllowed() {
			return quoteSharingAllowed;
		}
		/**
		 * @param quoteSharingAllowed the quoteSharingAllowed to set
		 */
		public void setQuoteSharingAllowed(String quoteSharingAllowed) {
			this.quoteSharingAllowed = quoteSharingAllowed;
		}
		/**
		 * @return the searchInfoTextSnippet
		 */
		public String getSearchInfoTextSnippet() {
			return searchInfoTextSnippet;
		}
		/**
		 * @param searchInfoTextSnippet the searchInfoTextSnippet to set
		 */
		public void setSearchInfoTextSnippet(String searchInfoTextSnippet) {
			this.searchInfoTextSnippet = searchInfoTextSnippet;
		}
		/**
		 * @return the sellerbooks
		 */
		public sellerBooks getSellerbooks() {
			return sellerbooks;
		}
		/**
		 * @param sellerbooks the sellerbooks to set
		 */
		public void setSellerbooks(sellerBooks sellerbooks) {
			this.sellerbooks = sellerbooks;
		}
		/**
		 * @return the OTHER
		 */
		public String getOTHER() {
			return OTHER;
		}
		/**
		 * @param OTHER the OTHER to set
		 */
		public void setOTHER(String OTHER) {
			this.OTHER = OTHER;
		}


}
