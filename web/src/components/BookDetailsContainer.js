import React from 'react';
import '../css/book.css';

class BookDetailsContainer extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      value: null,
    };
  }

  render() {
    return (

      <div className="book-details">
        <div className="card">
          <div className="card-body">
            <h5 className="card-title">{this.props.data.name}</h5>
            <h6 className="card-subtitle text-muted">Author: {this.props.data.author}</h6>
            {this.props.data.series != null &&
              <p className="card-text">{this.props.data.series}</p>
            }
          </div>
        </div>
      </div>
    );
  }
}

export default BookDetailsContainer;