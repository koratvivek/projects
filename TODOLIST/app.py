from flask import Flask, request, render_template, redirect
from flask_sqlalchemy import SQLAlchemy
from datetime import datetime

app = Flask(__name__)
app.config["SQLALCHEMY_DATABASE_URI"] = "sqlite:///todo.db"
app.config['SQLALCHEMY_TRACK_MODIFICATIONS'] = False
db = SQLAlchemy(app)


class Todo(db.Model):
    sno = db.Column(db.Integer, primary_key=True)
    title = db.Column(db.String(100), nullable=False)
    desc = db.Column(db.String(300), nullable=False)
    date_ = db.Column(db.DateTime, default=datetime.utcnow)

    def __repr__(self) -> str:
        return f"{self.sno}-{self.title}"


@app.route('/', methods=['GET', 'POST'])
def todo():
    if request.method == "POST":
        title = request.form['title']
        desc = request.form['description']
        todo = Todo(title=title, desc=desc)
        db.session.add(todo)
        db.session.commit()
    alltodo = Todo.query.all()
    return render_template('index.html', alltodo=alltodo)


@app.route('/delete/<int:sno>')
def delete(sno):
    todo = Todo.query.filter_by(sno=sno).first()
    db.session.delete(todo)
    db.session.commit()
    alltodo = Todo.query.all()
    return render_template('index.html', alltodo=alltodo)


@app.route('/update/<int:sno>', methods=["GET", "POST"])
def update(sno):
    todo = Todo.query.filter_by(sno=sno).first()
    if request.method == "POST":
        title = request.form['title']
        desc = request.form['description']
        todo.title = title
        todo.desc = desc
        db.session.commit()
        return redirect('/')
    return render_template('update.html', todo=todo)


if __name__ == '__main__':
    with app.app_context():
        db.create_all()
    app.run(debug=True)
