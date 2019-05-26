import React from 'react';
import ReactDOM from 'react-dom';
// import logo from './logo.svg';
import './App.css';
import BookDetailsContainer from './components/BookDetailsContainer';
import bookSvc from './service/BookService';

class App extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      isPressed: false,
      searchKeyword: ''
    }

    // this.handleChange = this.handleChange.bind(this);
    this.keyPress = this.keyPress.bind(this);
  }

  async keyPress(e) {
    if (e.keyCode === 13 || e.type === 'click') {
      this.setState({ isPressed: true });
      // console.log(this.state.searchKeyword);
      try {
        const data = await bookSvc.search(this.state.searchKeyword );
        // console.log(data);
        const bookList = document.getElementById('book-list');

        const books = data.map((book) =>
          <BookDetailsContainer data={book} key={book.name}/>
        );


        ReactDOM.render(
          books,
          bookList
        );
      } catch (err) {
        alert('Failed to search book list');
        console.log('Error', err);
      }
      // data.forEach((book) => {


      // });

      // alert('Alert pressed');
      // put the login here

      this.setState({ isPressed: false });
    }
  }

  render() {
    return (
      <div className="App">

        <div className="container">
          <div className="row">
            <div className="input-group">

              <input
                type="text"
                className="form-control"
                placeholder="Author / Book name"
                aria-label="Author / Book name"
                aria-describedby="search-button"
                onKeyUp={this.keyPress}
                onChange={(e) => {
                  this.setState({
                    searchKeyword: e.target.value
                  })
                }}
              />

              <div className="input-group-append">
                <button className="btn btn-outline-secondary" disabled={this.state.isPressed} type="button" id="search-button" onKeyUp={this.keyPress} onClick={this.keyPress}>

                  <i className={"fas fa-spinner fa-spin " + (this.state.isPressed ? '' : 'd-none')}  ></i>

                  <i className={"fas fa-search " + (this.state.isPressed ? 'd-none' : '')}></i>

                </button>
              </div>
            </div>
          </div>
          <label>Result:</label>
          <div className="book-list" id="book-list">
            {/* <BookDetailsContainer data={{ name: 'abc', author: 'You' }} />
            <BookDetailsContainer data={{ name: 'abc', author: 'You' }} />
            <BookDetailsContainer data={{ name: 'abc', author: 'You' }} />
            <BookDetailsContainer data={{ name: 'abc', author: 'You' }} />
            <BookDetailsContainer data={{ name: 'abc', author: 'You' }} />
            <BookDetailsContainer data={{ name: 'abc', author: 'You' }} />
            <BookDetailsContainer data={{ name: 'abc', author: 'You' }} />
            <BookDetailsContainer data={{ name: 'abc', author: 'You' }} />
            <BookDetailsContainer data={{ name: 'abc', author: 'You' }} /> */}

          </div>
        </div>
      </div>
    )
  }
}

export default App;
