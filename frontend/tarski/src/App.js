import { useState } from "react"

function App() {
  const [formula, setFormula] = useState("")
  const [backendresponse, setBackendResponse] = useState(null)
  const [shapes, setShapes] = useState([])
  const [shape, setShape] = useState({"pos_x": 1, "pos_y": 2, "shape": "Triangle", "color": "Blue", "size": "Small", "name": "a"})
  const iframeString = '<iframe width="560" height="315" src="https://www.youtube.com/embed/L8XbI9aJOXk?si=seRWZYiIMash7OYK" title="YouTube video player" frameborder="0" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share" allowfullscreen></iframe>';

  const handleChange = (event) => {
    const value = event.target.value
    setFormula(value)
    console.log(formula)
  }

  const changePosX = (event) => {
    const value = event.target.value
    var localshape = shape
    localshape.pos_x = value
    setShape({...localshape})
  }

  const changePosY = (event) => {
    const value = event.target.value
    var localshape = shape
    localshape.pos_y = value
    setShape({...localshape})
  }

  const changeShape = (event) => {
    const value = event.target.value
    var localshape = shape
    localshape.shape = value
    setShape({...localshape})
  }

  const changeColor = (event) => {
    const value = event.target.value
    var localshape = shape
    localshape.color = value
    setShape({...localshape})
  }

  const changeSize = (event) => {
    const value = event.target.value
    var localshape = shape
    localshape.size = value
    setShape({...localshape})
  }

  const changeName = (event) => {
    const value = event.target.value
    var localshape = shape
    localshape.name = value
    setShape({...localshape})
  }

  const addShape = (event) => {
    var localshapes = shapes
    localshapes.push(shape)
    setShapes([...localshapes])
  }

  const handleSubmit = async (event) => {
    console.log(shapes)
    event.preventDefault()
    fetch("/tarski/validate", {
        method: "POST", 
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify(
            {
                "shapes": shapes,
                "formula": formula
            }
        ),
      }).then(response => {
        return response.text();
      })
      .then(data => {
        setBackendResponse(data)
      })
      .catch(error => {
        console.error('Error fetching data:', error);
      });
      
    }

  return (
    <div>
      <div className="Send_Formula">
        <header className="App-header">
          <form>
            <input type="text" id="datain" name="datain" onChange={handleChange}>
            </input>
          </form>
          <button type="button" onClick={handleSubmit}>Check formula!</button>
        </header>
        {backendresponse=="cat"&&
        (<div dangerouslySetInnerHTML={{ __html: iframeString }} />)
        }
        {backendresponse!="cat"&&
        (<h1>{backendresponse}</h1>)
        }
      </div>
      <div className="Create_Shape">
        <form>
          <input type="number" id="position_x" name="position_x" onChange={changePosX}>
          </input>
          <input type="number" id="position_y" name="position_y" onChange={changePosY}>
          </input>
        </form>
        <form>
          
            <label>
              <input type="radio" value="Triangle" id="triangle" name="shape" defaultChecked onChange={changeShape}>
              </input>
              Triangle
           </label><br/>
            <label>
             <input type="radio" value="Square" id="square" name="shape" onChange={changeShape}>
             </input>
             Square
           </label><br/>
           <label>
             <input type="radio" value="Circle" id="circle" name="shape" onChange={changeShape}>
              </input>
              Circle
            </label><br/>
          
        </form>
        <hr></hr>
        <form>
          <label>
            <input type="radio" value="Red" id="red" name="color" defaultChecked onChange={changeColor}>
            </input>
            Red
          </label><br/>
          <label>
            <input type="radio" value="Green" id="green" name="color" onChange={changeColor}>
            </input>
            Green
          </label><br/>
          <label>
            <input type="radio" value="Blue" id="blue" name="color" onChange={changeColor}>
            </input>
            Blue
          </label><br/>
        </form>
        <hr></hr>
        <form>
          <label>
            <input type="radio" value="Small" id="small" name="size" defaultChecked onChange={changeSize}>
            </input>
            Small
          </label><br/>
          <label>
            <input type="radio" value="Medium" id="medium" name="size" onChange={changeSize}>
            </input>
            Medium
          </label><br/>
          <label>
            <input type="radio" value="Large" id="large" name="size" onChange={changeSize}>
            </input>
            Large
          </label><br/>
        </form>
        <form>
          <input type="text" id="shape_name" name="shape_name" onChange={changeName}>
          </input>
          <button type="button" onClick={addShape}>Add shape!</button>
        </form>
        <h3>{shape.pos_x}</h3>
        <h3>{shape.pos_y}</h3>
        <h3>{shape.shape}</h3>
        <h3>{shape.color}</h3>
        <h3>{shape.size}</h3>
        <h3>{shape.name}</h3>
      </div>
    </div>
  );
}

export default App;
