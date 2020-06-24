package com.example.form;

/**
 * コメントの入力フォームについての情報.
 * 
 * @author kohei.takasaki
 *
 */
public class CommentForm {
	/** コメント者名 */
	private String name;

	/** コメント内容 */
	private String content;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public String toString() {
		return "CommentForm [name=" + name + ", content=" + content + "]";
	}

}
