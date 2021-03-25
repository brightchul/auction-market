import React from 'react';
import { Button, Comment, Form, Header, Item, TextArea } from 'semantic-ui-react';



interface Props{
  form: any;
  comments: any;
  handleChange: any;
  handleRegisterComment: any;
  handleToggleCommentMode: any;
  handleUpdateComment: any;
  handleDeleteComment: any;
}

const CommentsList : React.FC<Props> = ({
  form,
  comments,
  handleChange,
  handleRegisterComment,
  handleToggleCommentMode,
  handleUpdateComment,
  handleDeleteComment,
}) => {
  return (
    <div>
      <Comment.Group>
        <Header as="h3" dividing>
          댓글
        </Header>
        <Form reply onSubmit={handleRegisterComment}>
          <Form.TextArea
            name="content"
            value={form.content}
            onChange={handleChange}
          />

          <Button
            type="submit"
            content="Add Reply"
            labelPosition="left"
            icon="edit"
            primary
            onClick={handleRegisterComment}
          />
        </Form>
        {comments.map((comment: any) => (
          <Comment key={comment.id}>
            <Comment.Avatar src="https://react.semantic-ui.com/images/avatar/small/matt.jpg" />
            <Comment.Content>
              <Comment.Author as="a">Matt</Comment.Author>
              <Comment.Metadata>
                <div>Today at 5:42PM</div>
              </Comment.Metadata>
              <Comment.Text>
                {comment.isUpdateMode && (
                  <TextArea name="update" onChange={handleChange}>
                    {form.update}
                  </TextArea>
                )}
                {!comment.isUpdateMode && <p>{comment.content}</p>}
              </Comment.Text>
              <Comment.Actions>
                {!comment.isUpdateMode && (
                  <Comment.Action
                    onClick={() => {
                      handleToggleCommentMode(comment.id);
                    }}
                  >
                    수정
                  </Comment.Action>
                )}
                {comment.isUpdateMode && (
                  <Comment.Action
                    onClick={() => {
                      handleUpdateComment(comment.id);
                    }}
                  >
                    수정완료
                  </Comment.Action>
                )}
                <Comment.Action
                  onClick={() => {
                    handleDeleteComment(comment.id);
                  }}
                >삭제</Comment.Action>
                <Comment.Action>Reply</Comment.Action>
              </Comment.Actions>
            </Comment.Content>
          </Comment>
        ))}
      </Comment.Group>
    </div>
  );
}

export default CommentsList;