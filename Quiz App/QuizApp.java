package com.quizapp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class QuizApp {
    private JFrame frame;
    private JLabel questionLabel;
    private JRadioButton[] options;
    private ButtonGroup optionsGroup;
    private JButton nextButton;
    private int currentQuestionIndex;
    private int score;
    
    private String[][] questions = {
            {"What is the capital of France?", "Berlin", "Madrid", "Paris", "Rome", "Paris"},
            {"Which planet is known as the Red Planet?", "Earth", "Mars", "Jupiter", "Saturn", "Mars"},
            {"What is the largest ocean on Earth?", "Indian", "Atlantic", "Arctic", "Pacific", "Pacific"}
    };

    public QuizApp() {
        frame = new JFrame("Quiz Application");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);
        frame.setLayout(new BorderLayout());

        questionLabel = new JLabel("Question");
        frame.add(questionLabel, BorderLayout.NORTH);

        JPanel optionsPanel = new JPanel();
        optionsPanel.setLayout(new GridLayout(4, 1));
        options = new JRadioButton[4];
        optionsGroup = new ButtonGroup();
        for (int i = 0; i < 4; i++) {
            options[i] = new JRadioButton();
            optionsGroup.add(options[i]);
            optionsPanel.add(options[i]);
        }
        frame.add(optionsPanel, BorderLayout.CENTER);

        nextButton = new JButton("Next");
        nextButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                checkAnswer();
                currentQuestionIndex++;
                if (currentQuestionIndex < questions.length) {
                    loadQuestion(currentQuestionIndex);
                } else {
                    showResult();
                }
            }
        });
        frame.add(nextButton, BorderLayout.SOUTH);

        currentQuestionIndex = 0;
        score = 0;
        loadQuestion(currentQuestionIndex);
    }

    private void loadQuestion(int questionIndex) {
        questionLabel.setText(questions[questionIndex][0]);
        for (int i = 0; i < 4; i++) {
            options[i].setText(questions[questionIndex][i + 1]);
            options[i].setSelected(false);
        }
    }

    private void checkAnswer() {
        String selectedAnswer = null;
        for (JRadioButton option : options) {
            if (option.isSelected()) {
                selectedAnswer = option.getText();
                break;
            }
        }
        if (selectedAnswer != null && selectedAnswer.equals(questions[currentQuestionIndex][5])) {
            score++;
        }
    }

    private void showResult() {
        JOptionPane.showMessageDialog(frame, "Quiz Over! Your score: " + score + "/" + questions.length);
        frame.dispose();
    }

    public void display() {
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        QuizApp app = new QuizApp();
        app.display();
    }
}
